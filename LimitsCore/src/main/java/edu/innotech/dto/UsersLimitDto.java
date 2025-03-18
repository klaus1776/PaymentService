package edu.innotech.dto;

//public record UsersLimitDto(UsersLimit usersLimit) {
//(Long id, Long userId, Double limitValue)
public class UsersLimitDto {
    private Long id;
    private Long userId;
    private Double limitValue;

    public UsersLimitDto() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getLimitValue() {
        return limitValue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLimitValue(Double limitValue) {
        this.limitValue = limitValue;
    }
}
