package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.persistence.entity.type.OperationType;

import java.math.BigDecimal;
import java.util.Objects;

public class OperationDtoResponse extends DtoResponse{

    private String name;
    private OperationType operationType;
    private String description;
    private BigDecimal sum;

    public OperationDtoResponse(Operation operation) {
        super(operation.getId(), operation.getCreated(), operation.getUpdated());
        setName(operation.getName());
        setOperationType(operation.getOperationType());
        setDescription(operation.getDescription());
        setSum(operation.getSum());
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationDtoResponse that = (OperationDtoResponse) o;
        return Objects.equals(name, that.name) && operationType == that.operationType && Objects.equals(description, that.description) && Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operationType, description, sum);
    }
}
