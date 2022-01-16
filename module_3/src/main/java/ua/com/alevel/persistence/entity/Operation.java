package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.entity.type.OperationType;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "operations")
public class Operation extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(name = "description")
    private String description;

    @Column(name = "sum", precision = 20, scale = 2)
    private BigDecimal sum;

    public Operation() {
        super();
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
        Operation operation = (Operation) o;
        return Objects.equals(name, operation.name) &&
               operationType == operation.operationType &&
               Objects.equals(description, operation.description) &&
               Objects.equals(sum, operation.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operationType, description, sum);
    }

    @Override
    public String toString() {
        return  "[id: " + super.getId() +
                ", created: " + super.getCreated().toString() +
                ", updated: " + super.getUpdated().toString() +
                ", name: " + name +
                ", operation type: " + operationType +
                ", sum: " + sum +
                ", description: " + description +
                ']';
    }


}
