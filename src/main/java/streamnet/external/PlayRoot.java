package streamnet.external;

public class PlayRoot {

    private Long id;
    private Long memberId;
    private String contentsStatus;
    private Long contentsId;

    public Long getContentsId() {
        return contentsId;
    }

    public void setContentsId(Long contentsId) {
        this.contentsId = contentsId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getContentsStatus() {
        return contentsStatus;
    }
    public void setContentsStatus(String contentsStatus) {
        this.contentsStatus = contentsStatus;
    }

}
