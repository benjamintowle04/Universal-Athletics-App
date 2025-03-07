package com.universalathletics.memberInfo.entity;

//------------------------------- imports ------------------------------------//
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
// This import is crucial for handling the JSON serialization of bidirectional relationships
// It prevents infinite recursion when converting to JSON
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.universalathletics.skill.entity.SkillEntity;

//--------------------- MemberInfo Entity Class ------------------------------//
/**
 * Entity class representing member information in the Universal Athletics
 * system.
 * This class maps to the 'member_info' table in the database and contains
 * personal and contact information for each member.
 *
 */

@Entity
@Table(name = "Member_Info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoEntity {

  /**
   * Unique identifier for the member.
   * Auto-generated using database identity strategy.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Member_ID")
  private Integer id;

  /**
   * Member's first name.
   */
  @Column(name = "First_Name")
  private String firstName;

  /**
   * Member's last name.
   */
  @Column(name = "Last_Name")
  private String lastName;

  /**
   * Member's email address.
   * Used for communication and account identification.
   */
  @Column(name = "Email")
  private String email;

  /**
   * Member's contact phone number.
   * NOTE: This field has a size limit in the database.
   * If you're getting "Data too long for column 'Phone'" errors,
   * either shorten the phone number format or alter the database column.
   */
  @Column(name = "Phone")
  private String phone;

  /**
   * Member's biographical information.
   * Contains a brief description or background of the member.
   */
  @Column(name = "Biography")
  private String biography;

  /**
   * URL or path to member's profile picture.
   * This stores the URL returned from Google Cloud Storage after upload.
   */
  @Column(name = "Profile_Pic")
  private String profilePic;

  /**
   * Member's geographical location or address.
   */
  @Column(name = "Location")
  private String location;

  /**
   * Member's unique authentication token.
   */
  @Column(name = "Firebase_ID")
  private String firebaseID;

  // Define the junction table for many-to-many relationship with Skills
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinTable(name = "Member_Skill", joinColumns = @JoinColumn(name = "Member_ID", referencedColumnName = "Member_ID"), inverseJoinColumns = @JoinColumn(name = "SKill_ID", referencedColumnName = "Skill_ID"))

  // This annotation prevents infinite recursion during JSON
  // serialization/deserialization
  // It tells Jackson to ignore the "members" property in SkillEntity when
  // serializing/deserializing
  @JsonIgnoreProperties("members")
  private List<SkillEntity> skill;

  /**
   * Getter used by MemberInfoService to get the attached skills of a certain
   * member
   * 
   * @return skill, the instance list of skills for MemberInfo
   */
  public List<SkillEntity> getSkills() {
    return this.skill;
  }

  /**
   * Setter used by MemberInfoService to save a set of skills to a member
   * 
   * @param skill
   */
  public void setSkills(List<SkillEntity> skill) {
    this.skill = skill;
  }
}
