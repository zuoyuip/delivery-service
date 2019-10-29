package org.zuoyu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.zuoyu.util.UUIDGenerated;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 建议反馈
 * @author zuoyu
 */
@ApiModel(value="Suggest", description = "建议反馈")
@Data
@Accessors(chain = true)
@Table(name = "`TB_SUGGEST`")
public class Suggest implements Serializable {
    /**
     * 建议反馈的唯一标识
     */
    @Id
    @KeySql(genId = UUIDGenerated.class)
    @Column(name = "`SUGGEST_ID`")
    @ApiModelProperty(value="suggestId建议反馈的唯一标识")
    private String suggestId;

    /**
     * 建议反馈-建议者的安全用户唯一标识
     */
    @Column(name = "`SUGGEST_USER_ID`")
    @ApiModelProperty(value="suggestUserId建议反馈-建议者的安全用户唯一标识")
    private String suggestUserId;

    /**
     * 建议反馈-反馈内容
     */
    @Column(name = "`SUGGEST_CONTENT`")
    @ApiModelProperty(value="suggestContent建议反馈-反馈内容")
    private String suggestContent;

    /**
     * 建议反馈-反馈时间
     */
    @Column(name = "`SUGGEST_DATE`")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value="suggestDate建议反馈-反馈时间")
    private Date suggestDate;

    private static final long serialVersionUID = 1L;

    public Suggest(String suggestId, String suggestUserId, String suggestContent,
        Date suggestDate) {
        this.suggestId = suggestId;
        this.suggestUserId = suggestUserId;
        this.suggestContent = suggestContent;
        this.suggestDate = suggestDate;
    }

    public Suggest() {
        super();
    }
}