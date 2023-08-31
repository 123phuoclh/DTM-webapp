package org.example.Backend.model;

import org.example.Backend.dto.FriendDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Friend")
@SqlResultSetMapping(name = "FriendDTO", classes = {
        @ConstructorResult(targetClass = FriendDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "nickName", type = String.class),
                        @ColumnResult(name = "isFollow", type = Boolean.class),
                        @ColumnResult(name = "isFriend", type = Boolean.class),
                        @ColumnResult(name = "avatar", type = String.class),
                        @ColumnResult(name = "startDate", type = Date.class)
                }
        )
    }
)
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "nickName")
    private String nickName;
    @Column(name = "isFollow")
    private Boolean isFollow;
    @Column(name = "isFiend")
    private Boolean isFriend;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "startDate")
    private Date startDate;


    public Friend() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFollow() {
        return isFollow;
    }

    public void setFollow(Boolean follow) {
        isFollow = follow;
    }

    public Boolean getFriend() {
        return isFriend;
    }

    public void setFriend(Boolean friend) {
        isFriend = friend;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
