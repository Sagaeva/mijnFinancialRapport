package be.sagaeva.financial.manager.services;

import be.sagaeva.financial.manager.data.Expense;
import be.sagaeva.financial.manager.data.User;
import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.ExpenseFilterDto;
import be.sagaeva.financial.manager.repositories.ExpenseRepository;
import be.sagaeva.financial.manager.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;



    public List<ExpenseDto> getAllExpenses() {
        User user = userService.getLoggedInUser();

        List<Expense> list =  expenseRepository.findByUserId(user.getId());
        List<ExpenseDto>  expenseDtoList = list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return expenseDtoList;
    }

    public ExpenseDto saveExpenseDetails(ExpenseDto expenseDto) throws ParseException {
        Expense expense = mapToEntity(expenseDto);
        expense.setUser(userService.getLoggedInUser());

        expense = expenseRepository.save(expense);
        return mapToDTO(expense);
    }

    public void deleteExpense(String id) {
        Expense exitingExpense = getExpense(id);
        expenseRepository.delete(exitingExpense);
    }

    public ExpenseDto getExpenseById(String id) {
        Expense exitingExpense = getExpense(id);
        return mapToDTO(exitingExpense);

    }

    public List<ExpenseDto> getFilteredExpense(ExpenseFilterDto expenseFilterDto) throws ParseException {
        String keyword = expenseFilterDto.getKeyword();
        String sortBy = expenseFilterDto.getSortBy();
        String startDateString = expenseFilterDto.getStartDate();
        String endDateString = expenseFilterDto.getEndDate();
        Date startDate =  !startDateString.isEmpty() ? DateTimeUtil.convertStringToDate(startDateString) : new Date(0);
        Date endDate = !endDateString.isEmpty() ? DateTimeUtil.convertStringToDate(endDateString) : new Date(System.currentTimeMillis());
        User user = userService.getLoggedInUser();
        List<Expense> list = expenseRepository.findByNameContainingAndDateBetweenAndUserId(keyword, startDate, endDate, user.getId());
        List<ExpenseDto> filteredList = list.stream().map(this::mapToDTO)
                .collect(Collectors.toList());
        if(sortBy.equals("date")) {
            filteredList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        } else {
            filteredList.sort((o1, o2) -> o2.getAmount().compareTo(o1.getAmount()));
        }
        return filteredList;
    }

    public String totalExpenses(List<ExpenseDto> expenses) {
        BigDecimal sum = new BigDecimal(0);
        BigDecimal total = expenses.stream().map(x -> x.getAmount().add(sum))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "be"));
        return format.format(total);
    }

    private Expense getExpense(String id) {
        return expenseRepository.findByExpenseId(id)
                .orElseThrow(() -> new RuntimeException("Expense not found for the id" + id));
    }

    private ExpenseDto mapToDTO(Expense expense) {
        ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class);
        expenseDto.setDateString(DateTimeUtil.convertDateToString(expenseDto.getDate()));
        return expenseDto;
    }

    private Expense mapToEntity(ExpenseDto expenseDto) throws ParseException {
        Expense expense = modelMapper.map(expenseDto, Expense.class);
        if(expense.getId() == null) {
            expense.setExpenseId(UUID.randomUUID().toString());
        }
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDto.getDateString()));
        return expense;
    }


}
