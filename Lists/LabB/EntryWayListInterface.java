/**
 * An interface for the ADT stacks, queues, and deques
 * Entries in the list have positions that begin with 1
 * Can only insert and delete from beginning and end of list
 *
 * @author Richard Szeto
 * @course CS 111C Jessica Masters
 *
 * @param <T>
 */

public interface EntryWayListInterface<T>
{
	/** Task: Inserts a new entry to the beginning of the list.
	 *        Entries currently in the list increase by 1 position.
	 *        The list's size is increased by 1.
	 * 
	 * @param newEntry the object to be inserted as a new entry
	 * @return true if the insertion is successful, or
	 *         false if the list is full
	 */
    boolean insertHead(T newEntry);
    
    /** Task: Inserts a new entry to the end of the list.
     *        Entries currently in the list are unaffected.
     *        The list's size is increased by 1.
     * 
     * @param newEntry the object to be inserted as a new entry
     * @return true if the insertion is successful, or
     *         false if the list is full
     */
    boolean insertTail(T newEntry);
    
    /** Task: Deletes an entry at the beginning of the list.
     *        Entries currently in the list decrease by 1 position.
     *        The list's size is decreased by 1.
     * 
     * @return the object that was deleted, or null if empty
     */
    T deleteHead();
    
    /** Task: Deletes an entry at the end of the list.
     *        Entries currently in the list are unaffected.
     *        The list's size is decreased by 1.
     * 
     * @return the object that was deleted, or null if empty
     */
    T deleteTail();
    
    /** Task: Prints all the entries in ascending order of their position.
     *        Entries currently in the list are unaffected.
     *        The list's size does not change.
     * 
     */
    void display();
    
    /** Task: Searches the list for an object.
     *        Entries currently in the list are unaffected.
     *        The list's size does not change.
     * 
     * @param anEntry the object to be searched
     * @return the position of the found entry, or
     *         -1 if entry not found
     */
    int contains(T anEntry);
    
    /** Task: Determines if list is empty.
     *        Entries currently in the list are unaffected.
     *        The list's size does not change.
     * 
     * @return true if list is empty, or
     *         false if list is not empty
     */
    boolean isEmpty();
    
    /** Task: Determines if list is full.
     *        Entries currently in the list are unaffected.
     *        The list's size does not change.
     * 
     * @return true if the list is full, or
     *         false if the list is not full
     */
    boolean isFull();
}
