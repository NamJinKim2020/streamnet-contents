package streamnet;

import streamnet.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    ContentsRepository contentsRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverWatched_WatchAdd(@Payload Watched watched){

        if(watched.isMe()){
            System.out.println("##### listener WatchAdd : " + watched.toJson());

            Optional<Contents> contentsOptional = contentsRepository.findById(watched.getContentsId());
            Contents contents = contentsOptional.get();
            contents.setWatchCount(contents.getWatchCount() + 1L);

            contentsRepository.save(contents);
        }
    }

}
