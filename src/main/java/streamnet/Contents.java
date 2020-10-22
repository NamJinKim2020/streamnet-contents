package streamnet;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Contents_table")
public class Contents {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private Long watchCount;
    private String status;
    private Long contentsId;

    @PostPersist
    public void onPostPersist(){
        Enrolled enrolled = new Enrolled();
        BeanUtils.copyProperties(this, enrolled);
        enrolled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        streamnet.external.ContentsHistory contentsHistory = new streamnet.external.ContentsHistory();
        // mappings goes here
        contentsHistory.setContentsId(this.getContentsId());
        contentsHistory.setStatus(this.getStatus());
        contentsHistory.setTitle(this.getTitle());
        contentsHistory.setWatchCount(this.getWatchCount());
        ContentsApplication.applicationContext.getBean(streamnet.external.ContentsHistoryService.class)
                .insert(contentsHistory);

    }

    @PostUpdate
    public void onPostUpdate(){
        WatchCounted watchCounted = new WatchCounted();
        this.setStatus("SUCCESS");
        BeanUtils.copyProperties(this, watchCounted);
        watchCounted.publishAfterCommit();

    }

    @PreRemove
    public void onPreRemove(){
        ContentsStopped contentsStopped = new ContentsStopped();
        this.setStatus("DELETED");
        BeanUtils.copyProperties(this, contentsStopped);
        contentsStopped.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        streamnet.external.PlayRoot playRoot = new streamnet.external.PlayRoot();
        // mappings goes here
        //this.setStatus("DELETED");
        playRoot.setContentsId(this.getContentsId());
        playRoot.setContentsStatus(this.getStatus());

        ContentsApplication.applicationContext.getBean(streamnet.external.PlayRootService.class)
            .stop(playRoot);


    }


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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Long getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Long watchCount) {
        this.watchCount = watchCount;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
