package wtf.choco.veinminer.pattern;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import wtf.choco.veinminer.block.BlockList;
import wtf.choco.veinminer.block.VeinMinerBlock;
import wtf.choco.veinminer.block.VeinMinerBlockWildcard;
import wtf.choco.veinminer.platform.world.BlockState;

/**
 * Utility methods for implementing {@link VeinMiningPattern VeinMiningPatterns}.
 */
public final class PatternUtils {

    private PatternUtils() { }

    /**
     * Check whether or not the given {@link BlockState} matches either the {@link VeinMinerBlock} or is
     * present in the given {@link BlockList}.
     *
     * @param block the block against which to check the state
     * @param aliasList the alias list, or null if no aliases
     * @param current the current block state to check
     *
     * @return true if {@code current} either {@link VeinMinerBlock#matchesState(BlockState) matches state}
     * with the {@code block} or is {@link BlockList#containsState(BlockState) contained in the alias list},
     * false otherwise
     *
     * @deprecated does not correctly handle wildcard states. Use
     * {@link #typeMatches(VeinMinerBlock, BlockList, BlockState, BlockState)} instead
     */
    @Deprecated
    public static boolean typeMatches(@NotNull VeinMinerBlock block, @Nullable BlockList aliasList, @NotNull BlockState current) {
        return block.matchesState(current) || (aliasList != null && aliasList.containsState(current));
    }

    /**
     * Check whether or not the given {@link BlockState} matches either the {@link VeinMinerBlock} or is
     * present in the given {@link BlockList}.
     *
     * @param block the block against which to check the state
     * @param aliasList the alias list, or null if no aliases
     * @param origin the origin block that was broken
     * @param current the current block state to check
     *
     * @return true if {@code current} either {@link VeinMinerBlock#matchesState(BlockState) matches state}
     * with the {@code block} or is {@link BlockList#containsState(BlockState) contained in the alias list},
     * false otherwise
     */
    public static boolean typeMatches(@NotNull VeinMinerBlock block, @Nullable BlockList aliasList, @NotNull BlockState origin, @NotNull BlockState current) {
        if (block instanceof VeinMinerBlockWildcard) {
            return origin.getType() == current.getType() || (aliasList != null && aliasList.containsState(current));
        }

        return block.matchesState(current) || (aliasList != null && aliasList.containsState(current));
    }

}
