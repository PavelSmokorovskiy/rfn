package retailcx.middleware.dto;

import javax.validation.constraints.NotBlank;

public class ProductDto {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String memberActivityTypeId = "string";

    private String endDate = "2020-01-21T04:13:13.851Z";

    private String startDate = "2020-01-21T04:13:13.851Z";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberActivityTypeId() {
        return memberActivityTypeId;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
