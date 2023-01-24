import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TeamFrontend implements ITeam{
	List<IPlayer> list;
	public TeamFrontend() {
		list = new ArrayList<IPlayer>();
	}
	/**
     * Compute an average statistic of the team
     *
     * @param stat either "home runs", "batting average", or "sprint speed"
     * @return the averaged statistic
     */
    public double averageStat(String stat) {
    	if(stat.equals("home runs")) return 53;
    	if(stat.equals("batting average")) return 265;
    	return 29;
    }

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<IPlayer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(IPlayer e) {
		list.add(e);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		list.remove(o);
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends IPlayer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends IPlayer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPlayer get(int index) {
		
		return list.get(index);
	}

	@Override
	public IPlayer set(int index, IPlayer element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, IPlayer element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPlayer remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<IPlayer> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<IPlayer> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IPlayer> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
