package com.bbz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="yx_video")
public class Video {
    @Id
    private String id;
    private String title;//标题
    private String brief;//简介

    @Column(name = "cover_path")
    private String coverPath;//封面
    @Column(name = "upload_time")
    private Date uploadTime;//上传时间
    @Column(name = "like_count")
    private Integer likeCount;//点赞数
    @Column(name = "play_count")
    private Integer playCount;//播放数
    @Column(name = "category_id")
    private String categoryId;//类别ID
    @Column(name = "user_id")
    private String userId;//用户ID
    @Column(name = "group_id")
    private String groupId;//分组
    @Column(name = "video_path")
    private String videoPath;//视频
    private String status;//状态


}
