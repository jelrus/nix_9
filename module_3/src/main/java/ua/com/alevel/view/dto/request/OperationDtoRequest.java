package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.type.OperationType;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

public class OperationDtoRequest extends DtoRequest{

    private String name;
    private OperationType operationType;
    private String description;
    private BigDecimal sum;
    private Long accountId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
