package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.Searcher.findOneBy;

public class Repository
{
    protected final List<Identifyable> data = new ArrayList<>();

    public void save(Identifyable element)
    {
        if (findById(element.getId()) != null)
        {
            throw new UniqueConstraintException("id must be unique");
        }
        data.add(element);
    }

    public Identifyable findById(int id) {
        return findOneBy(data, element -> element.getId() == id);
    }
    public List<Identifyable> findAll(){
        return Collections.unmodifiableList(data);
    }
}
