package retailcx.middleware.dto;

import net.minidev.json.JSONObject;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class ProductDto {

    private List<CategoryDto> supercategories;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("EndDate", "2020-02-04T08:00:45.052Z");
        jsonObject.put("ExternalId", code);
        jsonObject.put("MemberActivityTypeId", "string");
        jsonObject.put("Name", name);
        jsonObject.put("StartDate", "2020-02-04T08:00:45.052Z");
        return jsonObject;
    }

    public List<CategoryDto> getSupercategories() {
        return supercategories;
    }

    public void setSupercategories(List<CategoryDto> supercategories) {
        this.supercategories = supercategories;
    }

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

    @Override
    public String toString() {
        return "ProductDto{" +
                "supercategories=" + supercategories.toString() +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
