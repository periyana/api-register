package com.myjasakube.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @Email(message = "Email tidak valid")
    @NotEmpty(message = "Email tidak boleh kosong")
    private String email;

    @NotEmpty(message = "Nama lengkap tidak boleh kosong")
    private String fullName;

    @NotEmpty(message = "Jenis kelamin tidak boleh kosong")
    @Pattern(regexp = "Male|Female", message = "Gender harus Male atau Female")
    private String gender;

    @NotEmpty(message = "Nomor telepon tidak boleh kosong")
    @Pattern(regexp = "^\\d{10,13}$", message = "Nomor telepon harus terdiri dari 10 hingga 13 digit angka")
    private String phoneNumber;

    @NotEmpty(message = "Alamat tidak boleh kosong")
    private String address;

    @NotEmpty(message = "Password tidak boleh kosong")
    @Size(min = 8, message = "Password harus minimal 8 karakter")
    private String password;

    @NotEmpty(message = "Konfirmasi password tidak boleh kosong")
    private String confirmPassword;
}
