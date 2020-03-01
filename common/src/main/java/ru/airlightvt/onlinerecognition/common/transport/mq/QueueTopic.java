package ru.airlightvt.onlinerecognition.common.transport.mq;

import lombok.Getter;

@Getter
public enum QueueTopic {
    ADD_ADVERT_REQ("UPDATE.ADVERT.INPUT"),
    ADD_ADVERT_RES("UPDATE.ADVERT.OUTPUT"),
    UPDATE_ADVERT_REQ("UPDATE.ADVERT.INPUT"),
    UPDATE_ADVERT_RES("UPDATE.ADVERT.OUTPUT"),
    SEARCH_PET_REQ("SEARCH.PET.INPUT"),
    CREATE_PET_RES("SEARCH.PET.OUTPUT");

    private final String channelName;

    QueueTopic(String channelName) {
        this.channelName = channelName;
    }
}
