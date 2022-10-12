package com.ninjaone.backendinterviewproject.model.enumeration;

import lombok.Getter;

@Getter
public enum ActiveEnum {
	 	YES("YES", true),
	    NO("NO", false);

	    private final String name;
	    private final Boolean value;

	    ActiveEnum(String name, Boolean value) {
	        this.name = name;
	        this.value = value;
	    }
}
