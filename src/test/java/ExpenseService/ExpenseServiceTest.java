package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project iternalProject=new Project(ProjectType.INTERNAL,"internalProject");
        // when
        ProjectType projectType= iternalProject.getProjectType();
        // then
        assertSame(ProjectType.INTERNAL,projectType);

    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        ExpenseService expenseService=new ExpenseService();

        Project externalProject=new Project(ProjectType.EXTERNAL,"Project A");
        expenseService.getExpenseCodeByProjectTypeAndName(externalProject);
        // when
        ExpenseType expenseType=expenseService.getExpenseCodeByProjectTypeAndName(externalProject);
        // then
        assertSame(ExpenseType.EXPENSE_TYPE_A,expenseType);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        ExpenseService expenseService=new ExpenseService();
        Project externalProject=new Project(ProjectType.EXTERNAL,"Project B");
        expenseService.getExpenseCodeByProjectTypeAndName(externalProject);
        // when
        ExpenseType expenseType=expenseService.getExpenseCodeByProjectTypeAndName(externalProject);
        // then
        assertSame(ExpenseType.EXPENSE_TYPE_B,expenseType);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        ExpenseService expenseService=new ExpenseService();
        Project externalProject=new Project(ProjectType.EXTERNAL,"Other");
        expenseService.getExpenseCodeByProjectTypeAndName(externalProject);
        // when
        ExpenseType expenseType=expenseService.getExpenseCodeByProjectTypeAndName(externalProject);
        // then
        assertSame(ExpenseType.OTHER_EXPENSE,expenseType);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        ExpenseService expenseService=new ExpenseService();
        Project externalProject=new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"a");
        // when
        UnexpectedProjectTypeException unexpectedProjectTypeException=assertThrows(UnexpectedProjectTypeException.class,()->{
            ExpenseType expenseType=expenseService.getExpenseCodeByProjectTypeAndName(externalProject);
        });
        // then
        assertEquals("You enter invalid project type",unexpectedProjectTypeException.getMessage());
    }
}