var checkStraightLine = function(coordinates) {
    const x0 = coordinates[0][0], y0 = coordinates[0][1];
    const x = coordinates[1][0] - x0;
    const y = coordinates[1][1] - y0;
    for (let i = 2; i < coordinates.length; i++) {
        const xi = coordinates[i][0] - x0;
        const yi = coordinates[i][1] - y0;

        if (x * yi - y * xi) return false;
    }
    return true;
};