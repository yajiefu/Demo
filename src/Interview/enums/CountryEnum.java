package Interview.enums;

public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element: myArray) {
            if (index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
