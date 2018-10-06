package ru.airlightvt.onlinerecognition.queue.model;

public enum QueueMessageType {
    UPDATE_ARTICLE_REQ("updateArticleReq"),
    UPDATE_ARTICLE_RES("updateArticleRes"),
    SEARCH_PET_REQ("searchPetReq"),
    CREATE_PET_RES("searchPetRes");

    private final String channelName;

    private QueueMessageType(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }
}
