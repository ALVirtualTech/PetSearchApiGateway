package ru.airlightvt.onlinerecognition.advert.saga.create.reply;

import io.eventuate.tram.commands.common.Outcome;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateAdvertReply implements Outcome {
    private final long advertId;
}
