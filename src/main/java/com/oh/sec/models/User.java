package com.oh.sec.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oh.sec.security.SnPasswordEncoder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="users")
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(nullable=false, unique=true)
	@NotNull
	private String name;

	@JsonIgnore
	@Column(nullable=false)
	@NotNull
	private String password;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@JsonIgnore
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name="user_role",
			joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private Set<Role> roles;

	public void setPassword(@NotNull String password) {
		this.password = SnPasswordEncoder.encode(password);
	}
}
