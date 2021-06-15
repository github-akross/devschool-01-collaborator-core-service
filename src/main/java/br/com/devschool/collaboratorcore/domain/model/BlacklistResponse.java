package br.com.devschool.collaboratorcore.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BlacklistResponse implements Serializable {
    boolean result;
}
