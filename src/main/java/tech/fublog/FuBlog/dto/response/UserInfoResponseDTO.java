package tech.fublog.FuBlog.dto.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoResponseDTO {
    private Long id;
    private String fullName;
    private String picture;
    private String email;
    private String role;
    private List<String> roles;
    private Double point;
    private String username;

    public UserInfoResponseDTO(Long id, String fullName, String picture, String email, String role, List<String> roles, Double point) {
        this.id = id;
        this.fullName = fullName;
        this.picture = picture;
        this.email = email;
        this.role = role;
        this.roles = roles;
        this.point = point;
    }
}
