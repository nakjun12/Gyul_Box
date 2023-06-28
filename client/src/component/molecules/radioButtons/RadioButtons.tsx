import { ChangeEvent, useState } from "react";
import styles from "./RadioButtons.module.scss";
interface RadioButtonProps {
  label: string;
  value: string;
  checked: boolean;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

function RadioButton({ label, value, checked, onChange }: RadioButtonProps) {
  return (
    <>
      <label className={styles.label_style}>
        <input
          type="radio"
          value={value}
          checked={checked}
          onChange={onChange}
        />
        {label}
      </label>
    </>
  );
}

interface RadioButtonsProps {
  options: { label: string; value: string }[];
  defaultOption: string;
  onChange: (value: string) => void;
}

function RadioButtons({ options, defaultOption, onChange }: RadioButtonsProps) {
  const [selectedOption, setSelectedOption] = useState(defaultOption);

  const handleOptionChange = (event: ChangeEvent<HTMLInputElement>) => {
    setSelectedOption(event.target.value);
    onChange(event.target.value);
  };

  return (
    <div className={styles.radio_buttons_wrapper}>
      {options.map((option) => (
        <RadioButton
          key={option.value}
          label={option.label}
          value={option.value}
          checked={selectedOption === option.value}
          onChange={handleOptionChange}
        />
      ))}
    </div>
  );
}

export default RadioButtons;
