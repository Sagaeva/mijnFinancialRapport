package be.sagaeva.financial.manager.services;

import be.sagaeva.financial.manager.data.Expense;
import be.sagaeva.financial.manager.data.Income;
import be.sagaeva.financial.manager.data.User;
import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import be.sagaeva.financial.manager.dto.IncomeFilterDto;
import be.sagaeva.financial.manager.repositories.IncomeRepository;
import be.sagaeva.financial.manager.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public List<IncomeDTO> getAllIncomes() {
        User user = userService.getLoggedInUser();
        List<Income> list =  incomeRepository.
                findByDateBetweenAndUserId(java.sql.Date.valueOf(LocalDate.now().withDayOfMonth(1)),
                        java.sql.Date.valueOf(LocalDate.now()), user.getId());
        List<IncomeDTO>  expenseDtoList = list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return expenseDtoList;
    }

    public IncomeDTO saveIncomeDetails(IncomeDTO incomeDTO) throws ParseException {
        Income income = mapToEntity(incomeDTO);
        if(!income.getDate().before(new Date())) {
            throw new RuntimeException("Future date is not allowed");
        }
        income.setUser(userService.getLoggedInUser());

        income = incomeRepository.save(income);
        return mapToDTO(income);
    }

    public void deleteIncome(String id) {
        Income exitingIncome = getIncome(id);
        incomeRepository.delete(exitingIncome);
    }

    public IncomeDTO getIncomeById(String id) {
        Income exitingIncome = getIncome(id);
        return mapToDTO(exitingIncome);

    }

    public List<IncomeDTO> getFilteredIncome(IncomeFilterDto incomeFilterDto) throws ParseException {
        String keyword = incomeFilterDto.getKeyword();
        String sortBy = incomeFilterDto.getSortBy();
        String startDateString = incomeFilterDto.getStartDate();
        String endDateString = incomeFilterDto.getEndDate();
        Date startDate =  !startDateString.isEmpty() ? DateTimeUtil.convertStringToDate(startDateString) : new Date(0);
        Date endDate = !endDateString.isEmpty() ? DateTimeUtil.convertStringToDate(endDateString) : new Date(System.currentTimeMillis());
        User user = userService.getLoggedInUser();
        List<Income> list = incomeRepository.findByNameContainingAndDateBetweenAndUserId(keyword, startDate, endDate, user.getId());
        List<IncomeDTO> filteredList = list.stream().map(this::mapToDTO)
                .collect(Collectors.toList());
        if(sortBy.equals("date")) {
            filteredList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        } else {
            filteredList.sort((o1, o2) -> o2.getAmount().compareTo(o1.getAmount()));
        }
        return filteredList;
    }

    public String totalIncomes(List<IncomeDTO> incomes) {
        BigDecimal sum = new BigDecimal(0);
        BigDecimal total = incomes.stream().map(x -> x.getAmount().add(sum))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "be"));
        return format.format(total);
    }

    private IncomeDTO mapToDTO(Income income) {
        IncomeDTO incomeDTO = modelMapper.map(income, IncomeDTO.class);
        incomeDTO.setDateString(DateTimeUtil.convertDateToString(incomeDTO.getDate()));
        return incomeDTO;
    }

    private Income mapToEntity(IncomeDTO incomeDTO) throws ParseException {
        Income income = modelMapper.map(incomeDTO, Income.class);
        if(income.getId() == null) {
            income.setIncomeId(UUID.randomUUID().toString());
        }
        income.setDate(DateTimeUtil.convertStringToDate(incomeDTO.getDateString()));
        return income;
    }

    private Income getIncome(String id) {
        return incomeRepository.findByIncomeId(id)
                .orElseThrow(() -> new RuntimeException("Income not found for the id" + id));
    }


}
