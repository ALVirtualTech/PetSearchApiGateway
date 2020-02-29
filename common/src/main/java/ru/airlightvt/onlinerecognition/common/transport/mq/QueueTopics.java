package ru.airlightvt.onlinerecognition.common.transport.mq;

public enum QueueTopics {
    UPDATE_ARTICLE_REQ("UPDATE.ADVERT.INPUT"),
    UPDATE_ARTICLE_RES("UPDATE.ADVERT.OUTPUT"),
    SEARCH_PET_REQ("SEARCH.PET.INPUT"),
    CREATE_PET_RES("SEARCH.PET.OUTPUT");

    private final String channelName;

    private QueueTopics(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }
}
