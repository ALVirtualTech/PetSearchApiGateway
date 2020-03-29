package ru.airlightvt.onlinerecognition.advert.saga.create.command;

import io.eventuate.tram.commands.common.Command;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ConfirmCreateAdvertCommand implements Command {
    private final long advertId;
}
