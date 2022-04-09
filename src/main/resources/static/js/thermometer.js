anychart.onDocumentReady(function () {
    // Create and return simple linear gauge
    function drawLinearGauge(value) {
        var gauge = anychart.gauges.linear();
        gauge.data([value]).padding(10, 0, 30, 0);
        gauge
            .tooltip()
            .useHtml(true)
            .format(function () {
                switch (this.pointer.id()) {
                    case '0':
                        return this.value + '°C';
                    case '1':
                        return (
                            this.value +
                            '°C' +
                            ' (' +
                            (this.value * 1.8 + 32).toFixed(1) +
                            '°F)'
                        );
                    default:
                }
            });

        // Set scale settings
        var scale = gauge.scale();
        scale.minimum(-25).maximum(25).ticks({interval: 5});

        // Set axis and axis settings
        var axis = gauge.axis();
        axis.scale(scale).width('0.5%').offset('-1%');

        // Set text formatter for axis labels
        axis.labels().useHtml(true).format('{%Value}°');

        return gauge;
    }

    // Create simple gauge
    var simpleGauge = drawLinearGauge(12);
    var simpleThermometer = simpleGauge.thermometer(0);

    // Set simple thermometer settings
    simpleThermometer
        .name('Thermometer')
        .id('0')
        .fill('#64b5f6')
        .stroke('#64b5f6');

    // Create gauge with extra axis
    var multiAxisGauge = drawLinearGauge(12);
    var multiAxisThermometer = multiAxisGauge.thermometer(0);
    multiAxisThermometer.name('Thermometer').id('1');

    // Add left axis with custom labels
    var axisLeft = multiAxisGauge.axis(0);
    axisLeft.minorTicks(true);
    axisLeft
        .labels()
        .useHtml(true)
        .format(function () {
            if (this.value > 0) {
                return (
                    '<span style="color:#dd2c00;">' + this.value + '°</span>'
                );
            }
            return (
                '<span style="color: #1976d2;">' + this.value + '°</span>'
            );
        });

    // Add custom right axis with custom labels
    var axisRight = multiAxisGauge.axis(1);
    axisRight.minorTicks(true);
    axisRight
        .labels()
        .useHtml(true)
        .format(function () {
            if (this.value > 32) {
                return '<span style="color:#dd2c00;">' + this.value + 'F</span>';
            }
            return '<span style="color: #1976d2;">' + this.value + 'F</span>';
        });
    axisRight.orientation('right').offset('3.5%');

    // Set scale Fahrenheit for right axis
    var Fscale = anychart.scales.linear();
    Fscale.minimum(-13).maximum(77).ticks({interval: 5});
    axisRight.scale(Fscale);

    // Create table to place thermometers
    var layoutTable = anychart.standalones.table();
    layoutTable
        .hAlign('center')
        .vAlign('middle')
        .useHtml(true)
        .fontSize(16)
        .cellBorder(null);

    // Put thermometers into the layout table
    layoutTable.contents([
        ['Thermometer Samples', null],
        [
            'Simple Thermometer',
            'Thermometer with Custom<br/>Celsius and Fahrenheit Scales'
        ],
        [simpleGauge, multiAxisGauge]
    ]);

    // Set height for first row in layout table
    layoutTable.getRow(0).height(40).fontSize(18);
    layoutTable.getRow(1).height(80).fontSize(14);

    // Merge cells in layout table where needed
    layoutTable.getCell(0, 0).colSpan(3);

    // Set container id and initiate drawing
    layoutTable.container('thermometer');
    layoutTable.draw();
});