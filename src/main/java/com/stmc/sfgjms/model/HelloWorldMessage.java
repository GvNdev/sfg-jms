package com.stmc.sfgjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by: GvN
 * Date: 07-Feb-26
 * Time: 23:09
 * Project Name: sfg-jms
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage implements Serializable {
    static final long serialVersionUID = 8721854490527553460L;

    private UUID id;
    private String message;
}
