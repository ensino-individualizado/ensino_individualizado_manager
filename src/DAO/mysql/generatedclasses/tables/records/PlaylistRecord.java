/**
 * This class is generated by jOOQ
 */
package DAO.mysql.generatedclasses.tables.records;


import DAO.mysql.generatedclasses.tables.Playlist;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.0"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlaylistRecord extends UpdatableRecordImpl<PlaylistRecord> implements Record2<Integer, Integer> {

	private static final long serialVersionUID = -1587332164;

	/**
	 * Setter for <code>ensinoindividualizado.playlist.idplaylist</code>.
	 */
	public void setIdplaylist(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.playlist.idplaylist</code>.
	 */
	public Integer getIdplaylist() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>ensinoindividualizado.playlist.tipo</code>.
	 */
	public void setTipo(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ensinoindividualizado.playlist.tipo</code>.
	 */
	public Integer getTipo() {
		return (Integer) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, Integer> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, Integer> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Playlist.PLAYLIST.IDPLAYLIST;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Playlist.PLAYLIST.TIPO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getIdplaylist();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getTipo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlaylistRecord value1(Integer value) {
		setIdplaylist(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlaylistRecord value2(Integer value) {
		setTipo(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlaylistRecord values(Integer value1, Integer value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached PlaylistRecord
	 */
	public PlaylistRecord() {
		super(Playlist.PLAYLIST);
	}

	/**
	 * Create a detached, initialised PlaylistRecord
	 */
	public PlaylistRecord(Integer idplaylist, Integer tipo) {
		super(Playlist.PLAYLIST);

		setValue(0, idplaylist);
		setValue(1, tipo);
	}
}
