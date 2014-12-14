package ro.InnovaTeam.cemeteryApp.service;

import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.LogEntry;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
public interface LogEntryService extends EntityService<LogEntry>{
    public List<LogEntry> findByFilter(Filter filter, String entityName);

    public List<LogEntry> findByFilter(Filter filter, String entityName, Integer entityId);
}