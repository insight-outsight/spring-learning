package org.springlearning.model;

public enum UserLevelEnum {


	Typical(1),Silver(2),Gold(3),Diamond(4);
	private final int value;
	
	private UserLevelEnum(int value){
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
    public static UserLevelEnum valueOf(int value) {
        for (UserLevelEnum  e : UserLevelEnum.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }
	
	@Override
	public String toString() {
		return this.value+"";
	}

	
}
