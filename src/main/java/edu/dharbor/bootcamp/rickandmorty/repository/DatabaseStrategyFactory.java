package edu.dharbor.bootcamp.rickandmorty.repository;

import edu.dharbor.bootcamp.rickandmorty.util.constant.DatabaseStrategyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Set;

@Component
public class DatabaseStrategyFactory {
    private EnumMap<DatabaseStrategyName, DatabaseStrategy> strategyList;

    @Autowired
    public DatabaseStrategyFactory(Set<DatabaseStrategy> strategySet) {
        this.createStrategy(strategySet);
    }

    public DatabaseStrategy getStrategy(String strategyNameString) {
        DatabaseStrategyName strategyName = DatabaseStrategyName.valueOf(strategyNameString.toUpperCase());
        return strategyList.get(strategyName);
    }

    private void createStrategy(Set<DatabaseStrategy> strategySet) {
        this.strategyList = new EnumMap<>(DatabaseStrategyName.class);
        strategySet.forEach(strategy -> strategyList.put(strategy.getStrategyName(), strategy));
    }
}
