/**
 * @param {number[][]} wall
 * @return {number}
 */
var leastBricks = function(wall) {
    const edgeFreq = new Map()
    let maxFreq = 0;
    for (let row = 0; row < wall.length; row++) {
        let edgePosition = 0
        for (let col = 0; col < wall[row].length - 1; col++) {
            const currBrickLength = wall[row][col]
            edgePosition += currBrickLength
            if (edgeFreq.has(edgePosition)) {
                edgeFreq.set(edgePosition, edgeFreq.get(edgePosition) + 1)
            } else {
                edgeFreq.set(edgePosition, 1)
            }
            maxFreq = Math.max(maxFreq, edgeFreq.get(edgePosition))
        }
    }
    return wall.length - maxFreq
};