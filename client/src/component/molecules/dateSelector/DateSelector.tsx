import { useState } from "react";

interface Option {
  value: string;
  label: string;
}

interface DateRange {
  startYear: number | undefined;
  endYear: number | undefined;
}

export default function DateRangeSelector() {
  const [dateRange, setDateRange] = useState<DateRange>({
    startYear: 2019,
    endYear: undefined,
  });

  const startYearOptions = createYearOptions();
  const endYearOptions = createYearOptions(dateRange.startYear);

  function createYearOptions(excludeYear: number = 2018): Option[] {
    const currentYear = new Date().getFullYear();
    const yearOptions = [];

    for (let year = currentYear; year >= 2019; year--) {
      if (year >= excludeYear) {
        const option = {
          value: year.toString(),
          label: `${year}년`,
        };
        yearOptions.push(option);
      }
    }

    return yearOptions;
  }

  function handleStartYearChange(event: any) {
    const value = parseInt(event.target.value);
    setDateRange((prevState) => ({ ...prevState, startYear: value }));
  }

  function handleEndYearChange(event: any) {
    const value = parseInt(event.target.value);
    setDateRange((prevState) => ({ ...prevState, endYear: value }));
  }

  return (
    <div>
      <select
        id="startYear"
        value={dateRange.startYear}
        onChange={handleStartYearChange}
      >
        <option value="">년도를 선택해주세요</option>
        {startYearOptions.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>

      <select
        id="endYear"
        value={dateRange.endYear}
        onChange={handleEndYearChange}
        disabled={!dateRange.startYear}
      >
        <option value="">년도를 선택해주세요</option>
        {endYearOptions.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
    </div>
  );
}
