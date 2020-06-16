package com.leave.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GenerateDetail implements Serializable {

	private String beanName;

	private List<BeanField> fields;

}
