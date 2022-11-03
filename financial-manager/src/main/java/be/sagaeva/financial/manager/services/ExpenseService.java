package be.sagaeva.financial.manager.services;

import be.sagaeva.financial.manager.data.Expense;
import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.repositories.ExpenseRepository;
import be.sagaeva.financial.manager.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;



    public List<ExpenseDto> getAllExpenses() {
        List<Expense> list =  expenseRepository.findAll();
        List<ExpenseDto>  expenseDtoList = list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return expenseDtoList;
    }

    public ExpenseDto saveExpenseDetails(ExpenseDto expenseDto) throws ParseException {
        Expense expense = mapToEntity(expenseDto);
        expense = expenseRepository.save(expense);
        return mapToDTO(expense);
    }

    private ExpenseDto mapToDTO(Expense expense) {
        ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class);
        expenseDto.setDateString(DateTimeUtil.convertDateToString(expenseDto.getDate()));
        return expenseDto;
    }

    private Expense mapToEntity(ExpenseDto expenseDto) throws ParseException {
        Expense expense = modelMapper.map(expenseDto, Expense.class);
        expense.setExpenseId(UUID.randomUUID().toString());
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDto.getDateString()));
        return expense;
    }
}
