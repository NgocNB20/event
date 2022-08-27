package repository.impl;

import org.springframework.stereotype.Repository;

import model.Mytable;
import repository.BaseRepository;
import repository.MytableRepository;

@Repository
public class MytableRepositoryImpl extends BaseRepository<Mytable, Integer> implements MytableRepository {
	

}
