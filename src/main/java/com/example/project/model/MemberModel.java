package com.example.project.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MemberModel {
    private  Members member;

    public MemberModel(){
        member = new Members();
    }

    public MemberModel(String jsonResponse) {
        Gson gson = new GsonBuilder().create();
        member = gson.fromJson(jsonResponse, Members.class);

    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.member);
    }

    public Members getMember() {
        return member;
    }

    public static class Members {
        private String username;
        private String password;
        private String fullname;
        private String age;
        private String gender;
        private String email;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }



}
