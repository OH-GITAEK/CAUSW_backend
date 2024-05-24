package net.causw.application.dto.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.causw.domain.model.board.BoardDomainModel;
import net.causw.domain.model.post.PostDomainModel;
import net.causw.domain.model.enums.Role;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BoardOfCircleResponseDto {

    @ApiModelProperty(value = "게시판 id 값", example = "uuid 형식의 String 값입니다.")
    private String id;

    @ApiModelProperty(value = "게시판 이름", example = "board_example")
    private String name;

    @ApiModelProperty(value = "작성 가능 여부", example = "true")
    private Boolean writable;

    @ApiModelProperty(value = "삭제 여부", example = "false")
    private Boolean isDeleted;

    @ApiModelProperty(value = "게시글 id", example = "uuid 형식의 String 값입니다.")
    private String postId;

    @ApiModelProperty(value = "게시글 제목", example = "post_title_example")
    private String postTitle;

    @ApiModelProperty(value = "게시글 작성자 이름", example = "post_writer_example")
    private String postWriterName;

    @ApiModelProperty(value = "게시글 작성자 id", example = "uuid 형식의 String 값입니다.")
    private String postWriterStudentId;

    @ApiModelProperty(value = "게시글 생성 시간", example =  "2024-01-26T18:40:40.643Z")
    private LocalDateTime postCreatedAt;

    @ApiModelProperty(value = "게시글 댓글 개수", example =  "12")
    private Long postNumComment;

    // FIXME: Port 분리 후 삭제 필요
    public static BoardOfCircleResponseDto from(
            BoardDomainModel boardDomainModel,
            Role userRole,
            PostDomainModel postDomainModel,
            Long numComment
    ) {
        return BoardOfCircleResponseDto.builder()
                .id(boardDomainModel.getId())
                .name(boardDomainModel.getName())
                .writable(boardDomainModel.getCreateRoleList().stream().anyMatch(str -> userRole.getValue().contains(str)))
                .isDeleted(boardDomainModel.getIsDeleted())
                .postId(postDomainModel.getId())
                .postTitle(postDomainModel.getTitle())
                .postWriterName(postDomainModel.getWriter().getName())
                .postWriterStudentId(postDomainModel.getWriter().getStudentId())
                .postCreatedAt(postDomainModel.getCreatedAt())
                .postNumComment(numComment)
                .build();
    }

    public static BoardOfCircleResponseDto from(
            BoardDomainModel boardDomainModel,
            Role userRole
    ) {
        return BoardOfCircleResponseDto.builder()
                .id(boardDomainModel.getId())
                .name(boardDomainModel.getName())
                .writable(boardDomainModel.getCreateRoleList().stream().anyMatch(str -> userRole.getValue().contains(str)))
                .isDeleted(boardDomainModel.getIsDeleted())
                .postNumComment(0L)
                .build();
    }
}
