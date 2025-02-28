package com.universalathletics.entities;

//------------------------------- imports ------------------------------------//
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
           */
          @Column(name = "Profile_Pic")
          private String profilePic;

          /**
           * Member's geographical location or address.
           */
          @Column(name = "Location")
          private String location;
}
