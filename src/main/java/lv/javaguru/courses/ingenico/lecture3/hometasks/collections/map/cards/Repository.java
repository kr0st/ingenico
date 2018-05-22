package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.Searcher.findOneBy;

public class Repository <T extends Identifyable>
{
    protected final List<T> data = new ArrayList<>();

    public void save(T element)
    {
        if (findById(element.getId()) != null)
        {
            throw new UniqueConstraintException("id must be unique");
        }
        data.add(element);
    }

    public T findById(int id) {
        return findOneBy(data, element -> element.getId() == id);
    }
    public List<T> findAll(){
        return Collections.unmodifiableList(data);
    }
}
