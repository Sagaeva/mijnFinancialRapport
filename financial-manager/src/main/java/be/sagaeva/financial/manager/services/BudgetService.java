package be.sagaeva.financial.manager.services;

import be.sagaeva.financial.manager.data.Expense;
import be.sagaeva.financial.manager.data.Income;
import be.sagaeva.financial.manager.data.User;
import be.sagaeva.financial.manager.dto.ExpenseDto;
import be.sagaeva.financial.manager.dto.IncomeDTO;
import be.sagaeva.financial.manager.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;





    }





