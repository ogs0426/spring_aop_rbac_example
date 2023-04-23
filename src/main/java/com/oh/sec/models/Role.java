package com.oh.sec.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="roles")
public class Role
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private Integer id;
	@Column(nullable=false, unique=true)
	@NotNull
	private String name;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@JsonIgnore
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name="role_right",
			joinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="RIGHT_ID", referencedColumnName="ID")})
	private Set<Right> rights;
}
