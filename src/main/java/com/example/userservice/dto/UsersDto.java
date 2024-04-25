package com.example.userservice.dto;

public class UsersDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String token;

    public UsersDto() {
    }

    public UsersDto(Long id, String firstName, String lastName, String login, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.token = token;
    }
    
    public UsersDto(String login, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Builder pattern implementation
    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String login;
        private String token;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public UsersDto build() {
            return new UsersDto(id, firstName, lastName, login, token);
        }
    }
}
