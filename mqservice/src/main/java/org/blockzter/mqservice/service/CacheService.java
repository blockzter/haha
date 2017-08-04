package org.blockzter.mqservice.service;

import org.blockzter.mqservice.model.dto.NodeDTO;

/**
 * Created by mblock2 on 1/29/17.
 */
public interface CacheService {
	/**
	 * Clear cache of all nodes
	 */
	void clear();

	/**
	 * Add a node.
	 *
	 * @param node
	 */
	void addNode(NodeDTO node);

	/**
	 * update the node.id with node data...
	 * @param node source node data to use.
	 * @return updated node
	 */
	NodeDTO updateNode(NodeDTO node);

	/**
	 * Add as new or update an existing node
	 * @param node source node data to use.
	 * @return updated node
	 */
	NodeDTO addUpdateNode(NodeDTO node);

	/**
	 * Remove node by ID
	 * @param id node id
	 * @return node or null if not found
	 */
	NodeDTO removeNode(Integer id);

	/**
	 * Remove node by ID
	 * @param node node
	 * @return node or null if not found
	 */
	NodeDTO removeNode(NodeDTO node);

	/**
	 * get node by id
	 * @param id node id
	 * @return node or null if not found
	 */
	NodeDTO getNode(Integer id);
	/**
	 * get node by id
	 * @param node node
	 * @return node or null if not found
	 */
	NodeDTO getNode(NodeDTO node);

	/**
	 * save current state to a file
	 * @param fileName save to file
	 */
	void save(String fileName);

	/**
	 * load state from a file
	 * @param fileName load from file
\	 */
	void load(String fileName);

	// Malvern Day camp 8/21 - 8/25 - awaiting call back. Done!
	// Beachcomber -- 8/26- 9/2
}
