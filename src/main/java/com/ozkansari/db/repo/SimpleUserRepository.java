package com.ozkansari.db.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ozkansari.db.entity.DBUser;

public interface SimpleUserRepository extends CrudRepository<DBUser, Long> {

	/**
	 * Find the user with the given username. This method will be translated into a query using the
	 * {@link javax.persistence.NamedQuery} annotation at the {@link DBUser} class.
	 * 
	 * @param lastname
	 * @return
	 */
	DBUser findByTheUsersName(String username);

	/**
	 * Uses {@link Optional} as return and parameter type.
	 * 
	 * @param username
	 * @return
	 */
	Optional<DBUser> findByUsername(Optional<String> username);

	/**
	 * Find all users with the given lastname. This method will be translated into a query by constructing it directly
	 * from the method name as there is no other query declared.
	 * 
	 * @param lastname
	 * @return
	 */
	List<DBUser> findByLastname(String lastname);

	/**
	 * Returns all users with the given firstname. This method will be translated into a query using the one declared in
	 * the {@link Query} annotation declared one.
	 * 
	 * @param firstname
	 * @return
	 */
	@Query("select u from DBUser u where u.firstname = :firstname")
	List<DBUser> findByFirstname(@Param("firstname") String firstname);

	/**
	 * Returns all users with the given name as first- or lastname. This makes the query to method relation much more
	 * refactoring-safe as the order of the method parameters is completely irrelevant.
	 * 
	 * @param name
	 * @return
	 */
	@Query("select u from DBUser u where u.firstname = :name or u.lastname = :name")
	List<DBUser> findByFirstnameOrLastname(@Param("name") String name);

	/**
	 * Returns the total number of entries deleted as their lastnames match the given one.
	 * 
	 * @param lastname
	 * @return
	 */
	Long removeByLastname(@Param("lastname") String lastname);

	/**
	 * Returns a {@link Slice} counting a maximum number of {@link Pageable#getPageSize()} users matching given criteria
	 * starting at {@link Pageable#getOffset()} without prior count of the total number of elements available.
	 * 
	 * @param lastname
	 * @param page
	 * @return
	 */
	Slice<DBUser> findByLastnameOrderByUsernameAsc(@Param("lastname") String lastname, Pageable page);

	/**
	 * Return the first 2 users ordered by their lastname asc.
	 * 
	 * <pre>
	 * Example for findFirstK / findTopK functionality.
	 * </pre>
	 * 
	 * @return
	 */
	List<DBUser> findFirst2ByOrderByLastnameAsc();

	/**
	 * Return the first 2 users ordered by the given {@code sort} definition.
	 * 
	 * <pre>
	 * This variant is very flexible because one can ask for the first K results when a ASC ordering
	 * is used as well as for the last K results when a DESC ordering is used.
	 * </pre>
	 * 
	 * @param sort
	 * @return
	 */
	List<DBUser> findTop2By(Sort sort);

	/**
	 * Return all the users with the given firstname or lastname. Makes use of SpEL (Spring Expression Language).
	 *
	 * @param user
	 * @return
	 */
	@Query("select u from DBUser u where u.firstname = :#{#user.firstname} or u.lastname = :#{#user.lastname}")
	Iterable<DBUser> findByFirstnameOrLastname(DBUser user);
}
