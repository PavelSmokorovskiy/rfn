package retailcx.middleware.dto;

import net.minidev.json.JSONObject;

import javax.validation.constraints.NotBlank;

public class CategoryDto {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("EndDate", "2020-02-04T08:00:45.052Z");
        jsonObject.put("ExternalId", code);
        jsonObject.put("Name", name);
        jsonObject.put("StartDate", "2020-02-04T08:00:45.052Z");
        return jsonObject;
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
        return "CategoryDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
