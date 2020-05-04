package com.sa.recipe.converters;

import com.sa.recipe.commands.NoteCommand;
import com.sa.recipe.domain.Note;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToNote implements Converter<NoteCommand, Note> {
    @Override
    public Note convert(NoteCommand noteCommand) {
        if (null == noteCommand) {
            return null;
        }

        return Note.builder()
                .id(noteCommand.getId())
                .notes(noteCommand.getNotes())
                .build();
    }
}
