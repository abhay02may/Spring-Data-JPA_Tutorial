package com.dailycodebuffer.springdata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Guardian {

    @Column(name = "GUARDIAN_NAME")
    private String guardianName;
    @Column(name = "GUARDIAN_EMAIL")
    private String guardianEmail;
    @Column(name = "GUARDIAN_MOBILE")
    private String guardianMobile;
}
